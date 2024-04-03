document.addEventListener("DOMContentLoaded", function () {
    let localSocket = new WebSocket("ws://localhost:5000/stomp")
    let stompClient = Stomp.over(localSocket);
    let messages = document.querySelector(".chat-messages");
    let sendButton = document.querySelector(".send-message-button");
    let messageContent = document.querySelector(".input-message");
    let currentUsername = document.querySelector(".current-user-name").textContent;
    let messageNotExisted = [];
    let receivedUserId = document.querySelector(".received-user-id").textContent;
    let currentUserId = document.querySelector(".current-user-id").textContent;
    let isInitMessage = false;
    stompClient.connect({}, function (qualifiedName, value) {


        stompClient.send("/init-messages/" + currentUserId + "-" + receivedUserId, {}, {});

        sendButton.addEventListener('click', function (event) {
            event.preventDefault();
            let currentTime = new Date();

            let hours = currentTime.getHours().toString().padStart(2, '0');
            let minutes = currentTime.getMinutes(). toString().padStart(2, '0');

            let formattedTime = `${hours}:${minutes}`;

            let messageData = {
                content: messageContent.value,
                sender: parseInt(currentUserId),
                receiver: parseInt(receivedUserId),
                senderName: currentUsername,
                type: "CHAT",
                timeStamp: formattedTime,
            };
            stompClient.send("/send-messages", {}, JSON.stringify(messageData));
            messageContent.value = '';
            readMessage(messageData);
        });
        stompClient.subscribe("/messages/" + currentUserId, function (message) {
            let receivedMessage = JSON.parse(message.body);
            if (Array.isArray(receivedMessage)) {
                isInitMessage = true;
                receivedMessage.forEach(receivedMessage => {
                    readMessage(receivedMessage);
                })
            } else readMessage(receivedMessage);
        });
    })

    function readMessage(receivedMessage){
        let parentDiv = document.createElement("div");
        parentDiv.classList.add("pb-4");
        if (receivedMessage.sender === parseInt(currentUserId)) parentDiv.classList.add("chat-message-right");
        else parentDiv.classList.add("chat-message-left");
        let avatarDiv = document.createElement("div");
        let avatarImg = document.createElement("img");
        avatarImg.setAttribute("src", "/avatar/default-avatar.jpg");
        avatarImg.width = 40;
        avatarImg.height = 40;
        avatarImg.classList.add("rounded-circle", "mr-1");
        let timeStamp = document.createElement("div");
        timeStamp.classList.add("text-muted", "small", "text-nowrap", "mt-2");
        timeStamp.textContent = receivedMessage.timeStamp;
        avatarDiv.appendChild(avatarImg);
        avatarDiv.appendChild(timeStamp);

        let messageDiv = document.createElement("div");
        messageDiv.classList.add("flex-shrink-1", "bg-light", "rounded", "py-2", "px-3", "mr-3");
        let senderDiv = document.createElement("div");
        senderDiv.classList.add("font-weight-bold", "mb-1");
        senderDiv.textContent = receivedMessage.senderName;
        let messageContent = document.createElement("p");
        messageContent.textContent = receivedMessage.content;
        messageDiv.appendChild(senderDiv)
        messageDiv.appendChild(messageContent);


        parentDiv.appendChild(avatarDiv);
        parentDiv.appendChild(messageDiv);

        if (isInitMessage && messageNotExisted[receivedMessage.id] === undefined) {
            messageNotExisted[receivedMessage.id] = "defined";
            messages.appendChild(parentDiv);
        } else messages.appendChild(parentDiv);
    }
})