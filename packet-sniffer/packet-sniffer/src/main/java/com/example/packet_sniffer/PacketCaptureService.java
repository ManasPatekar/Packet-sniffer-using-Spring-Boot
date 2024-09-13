package com.example.packet_sniffer;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PacketCaptureService {

    private PcapHandle handle;
    private List<String> packets = new ArrayList<>();
    private AtomicBoolean isCapturing = new AtomicBoolean(false);

    public void startCapture() throws PcapNativeException, NotOpenException {
        // Fetch all available network interfaces
        List<PcapNetworkInterface> allDevs = Pcaps.findAllDevs();
        if (allDevs == null || allDevs.isEmpty()) {
            throw new PcapNativeException("No network interfaces found. Please check your network configuration.");
        }

        // Use the first available network interface (you can select based on specific criteria)
        PcapNetworkInterface nif = allDevs.get(0); // Optionally, replace with the desired interface
        
        System.out.println("Using network interface: " + nif.getName() + " - " + nif.getDescription());

        // Open the network interface for packet capture
        handle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);

        // Set the capturing state to true
        isCapturing.set(true);

        // Start packet capture in a new thread
        new Thread(() -> {
            try {
                handle.loop(-1, new PacketListener() { // Replace PCAP_INFINITE with -1
                    @Override
                    public void gotPacket(Packet packet) {
                        if (isCapturing.get()) {
                            packets.add(packet.toString());
                            System.out.println(packet); // Display packet details in the console
                        }
                    }
                });
            } catch (PcapNativeException | InterruptedException | NotOpenException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public List<String> getCapturedPackets() {
        return new ArrayList<>(packets); // Return a copy of the packets list
    }

    public void stopCapture() {
        if (isCapturing.get() && handle != null) {
            // Safely stop capturing packets
            try {
                isCapturing.set(false);
                handle.breakLoop(); // Break the capture loop
            } catch (NotOpenException e) {
                e.printStackTrace();
            } finally {
                handle.close(); // Close the handle when finished
            }
        }
    }

    // Optional method to list available network interfaces for debugging or selection
    public List<String> listAvailableInterfaces() throws PcapNativeException {
        List<PcapNetworkInterface> allDevs = Pcaps.findAllDevs();
        List<String> interfaceDetails = new ArrayList<>();
        
        for (PcapNetworkInterface dev : allDevs) {
            interfaceDetails.add(dev.getName() + " : " + dev.getDescription());
        }

        return interfaceDetails;
    }
}
