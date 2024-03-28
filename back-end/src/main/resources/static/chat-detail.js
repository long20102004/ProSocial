document.addEventListener("DOMContentLoaded", function () {
    let socket = new WebSocket("ws://longhh-chatting.us-east-1.elasticbeanstalk.com/stomp");
    let localSocket = new WebSocket("ws://localhost:5000/stomp")
    let stompClient = Stomp.over(localSocket);
    let messages = document.querySelector(".list-message");
    let sendButton = document.querySelector(".send-message-button");
    let messageContent = document.querySelector(".input-message");
// let receivedUsername = document.querySelector(".received-user-name");
    let currentUsername = "TEST";
    stompClient.connect({}, function () {
        let receivedUserId = document.querySelector(".received-user-id").textContent;
        let currentUserId = document.querySelector(".current-user-id").textContent;
        if (parseInt(receivedUserId) > parseInt(currentUserId)) {
            let temp = receivedUserId;
            receivedUserId = currentUserId
            currentUserId = temp;
        }
        let firstMessage = {
            sender: currentUsername,
            content: currentUsername + " just joined the chat!",
            type: "JOIN"
        };
        stompClient.send("/send-messages/" + receivedUserId + "-" + currentUserId, {}, JSON.stringify(firstMessage));
        stompClient.send("/init-messages/" + receivedUserId + "-" + currentUserId, {}, {});
        sendButton.addEventListener('click', function (event) {
            event.preventDefault();
            let messageData = {
                content: messageContent.value,
                sender: currentUsername,
                type: "CHAT",
                connection: receivedUserId + "-" + currentUserId
            };
            stompClient.send("/send-messages/" + receivedUserId + "-" + currentUserId, {}, JSON.stringify(messageData));
            messageContent.value = '';
        });
        stompClient.subscribe("/messages/" + receivedUserId + '-' + currentUserId, function (message) {
            let receivedMessage = JSON.parse(message.body);
            let li = document.createElement("li");
            li.textContent = receivedMessage.content;
            messages.appendChild(li);
        });
        stompClient.subscribe("/init-chat", function (ListMessage){

        })
    })
})
