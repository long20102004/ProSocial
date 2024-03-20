var socket = new WebSocket("ws://localhost:8080/stomp");
var stompClient = Stomp.over(socket);
var messages = document.querySelector(".list-message");
var inputField = document.querySelector("input");
var sendButton = document.querySelector("button");

// Connect to the STOMP broker
stompClient.connect({}, function(frame) {
    // Connection is successful
    console.log('Connected: ' + frame);

    // Add an event listener to the send button
    sendButton.addEventListener('click', function(event) {
        // Prevent the form from being submitted
        event.preventDefault();

        // Get the value of the input field
        var messageText = inputField.value;

        // Send a message to a specific destination
        stompClient.send("/send-message", {}, messageText);

        // Clear the input field
        inputField.value = '';
    });

    // Subscribe to the topic
    stompClient.subscribe('/messages', function (message) {
        var li = document.createElement('li');
        li.textContent = message.body;
        messages.appendChild(li);
    });
});