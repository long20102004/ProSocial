let messages = document.querySelector(".list-message");
let sendButton = document.querySelector(".send-message-button");
let inputField = document.querySelector(".input-message");
let userName = document.body.dataset.user;
let socket = new WebSocket("ws://longhh-chatting.us-east-1.elasticbeanstalk.com/stomp");
let localSocket = new WebSocket("ws://localhost:5000/stomp")
let stompClient = Stomp.over(localSocket);

function receiveAllMessagesBefore() {

}
stompClient.connect({}, function (event) {
        let payload = {
            content: userName + " just joined the chat!",
            sender: userName,
            type: "JOIN"
        };

        stompClient.send("/send-message", {}, JSON.stringify(payload));

        sendButton.addEventListener('click', function (clickEvent) {
            clickEvent.preventDefault();
            console.log("sent");
            let message = {
                content: inputField.value,
                sender: userName,
                type: "CHAT"
            };
            stompClient.send("/send-message", {}, JSON.stringify(message));
            inputField.value = '';
        });



        receiveAllMessagesBefore();

        stompClient.subscribe("/messages", function (message) {

            let receivedPayload = JSON.parse(message.body);
            let li = document.createElement("li");
            if (receivedPayload.sender === userName) {
                li.classList.add("my-message");
            } else li.classList.add("other-message")
            li.textContent = receivedPayload.content;
            messages.appendChild(li);
        });
    }
);

