var socket = new WebSocket("ws://localhost:8080/stomp");
var stompClient = Stomp.over(socket);
var myMessage = document.querySelector(".my-list-message");
var sendButton = document.querySelector(".send-message-button");
var inputField = document.querySelector(".input-message");
stompClient.connect({}, function (event) {

    sendButton.addEventListener('click', function (event) {
        event.preventDefault();

        var message = inputField.value;

        stompClient.send("/send-message", {}, message);

        inputField.value = '';
    });
    stompClient.subscribe("/messages", function (message) {

        var li = document.createElement("li");

        li.textContent = message.body;

        myMessage.appendChild(li);
    });
}); 