package com.example.packet_sniffer;

//package com.example.packetsniffer;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PacketCaptureController {

    @Autowired
    private PacketCaptureService packetCaptureService;

    @GetMapping("/startCapture")
    public ResponseEntity<String> startCapture() {
        try {
            packetCaptureService.startCapture();
            return ResponseEntity.ok("Packet capture started.");
        } catch (PcapNativeException | NotOpenException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getCapturedPackets")
    public ResponseEntity<List<String>> getCapturedPackets() {
        return ResponseEntity.ok(packetCaptureService.getCapturedPackets());
    }

    @GetMapping("/stopCapture")
    public ResponseEntity<String> stopCapture() {
        packetCaptureService.stopCapture();
        return ResponseEntity.ok("Packet capture stopped.");
    }
}
