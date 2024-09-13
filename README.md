# Packet-sniffer-using-Spring-Boot
This project is a dynamic, real-time network packet sniffer developed using Java, Spring Boot, and the Pcap4j library. The application captures network packets and displays them on a user-friendly web interface, built with HTML, CSS, and JavaScript.

# Packet Sniffer in Spring Boot

This project is a real-time network packet sniffer built using Java, Spring Boot, and Pcap4j. It allows you to capture and analyze network packets, displaying them dynamically on a web-based interface. This tool is helpful for anyone interested in network monitoring, packet analysis, or understanding the fundamentals of how data moves through networks.

## Features

- **Real-Time Packet Capture**: Capture live network packets.
- **Start and Stop Capture**: Control the packet capture process through a simple interface.
- **Packet Visualization**: View captured packets with key details like source, destination, and packet size in a structured table format.
- **REST API Integration**: Expose APIs to start, stop, and fetch captured packets.

## Technologies Used

- **Java**: Handles the backend logic and packet capture functionality.
- **Spring Boot**: Provides the framework for building RESTful APIs and managing the web server.
- **Pcap4j**: A Java library used for network packet capturing and analysis.
- **HTML, CSS, JavaScript**: Builds the user interface for the packet display and controls.
- **Thymeleaf (Optional)**: If you want to use dynamic templating for HTML.

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- Java 11+
- Maven
- Network access and permissions to capture packets (sudo or admin privileges might be required).

## Getting Started

### Step 1: Clone the Repository


git clone https://github.com/your-username/packet-sniffer.git
cd packet-sniffer

Step 2:Build the Project
mvn clean install

Step 3:Run the Spring Boot Application
****Start the Spring Boot server by running:
mvn spr0ing-boot:run

Step 4:Access the Web Interface
Open your web browser and go to:
http://localhost:8080/packets.html


