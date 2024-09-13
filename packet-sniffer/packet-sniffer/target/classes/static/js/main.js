document.addEventListener('DOMContentLoaded', () => {
    const startButton = document.getElementById('startCapture');
    const stopButton = document.getElementById('stopCapture');
    const packetsList = document.getElementById('packetsList');

    startButton.addEventListener('click', () => {
        fetch('/api/startCapture')
            .then(response => response.text())
            .then(data => alert(data))
            .catch(error => console.error('Error:', error));
    });

    stopButton.addEventListener('click', () => {
        fetch('/api/stopCapture')
            .then(response => response.text())
            .then(data => alert(data))
            .catch(error => console.error('Error:', error));
    });

    function updatePackets() {
        fetch('/api/getCapturedPackets')
            .then(response => response.json())
            .then(data => {
                packetsList.innerHTML = data.map(packet => `<li>${packet}</li>`).join('');
            })
            .catch(error => console.error('Error:', error));
    }

    // Optionally, refresh the list of packets periodically
    setInterval(updatePackets, 5000);
});
