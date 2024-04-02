document.addEventListener("DOMContentLoaded", function () {
    let localSocket = new WebSocket("ws://localhost:5000/stomp")
    let stompClient = Stomp.over(localSocket);
    let messages = document.querySelector(".chat-messages");
    let sendButton = document.querySelector(".send-message-button");
    let messageContent = document.querySelector(".input-message");
    let currentUsername = document.querySelector(".current-user-name").textContent;
    let messageNotExisted = [];
    let previousMessage = [];
    let isReading = false;


    stompClient.connect({}, function (qualifiedName, value) {
        let receivedUserId = document.querySelector(".received-user-id").textContent;
        let currentUserId = document.querySelector(".current-user-id").textContent;
        if (parseInt(receivedUserId) > parseInt(currentUserId)) {
            let temp = receivedUserId;
            receivedUserId = currentUserId
            currentUserId = temp;
        }

        stompClient.send("/init-messages/" + receivedUserId + "-" + currentUserId, {}, {});

        sendButton.addEventListener('click', function (event) {
            event.preventDefault();
            let currentTime = new Date();

            let hours = currentTime.getHours().toString().padStart(2, '0');
            let minutes = currentTime.getMinutes(). toString().padStart(2, '0');

            let formattedTime = `${hours}:${minutes}`;

            let messageData = {
                content: messageContent.value,
                sender: currentUsername,
                type: "CHAT",
                timeStamp: formattedTime,
                connection: receivedUserId + "-" + currentUserId
            };

            stompClient.send("/send-messages/" + receivedUserId + "-" + currentUserId, {}, JSON.stringify(messageData));
            messageContent.value = '';
        });
        stompClient.subscribe("/messages/" + receivedUserId + '-' + currentUserId, function (message) {
            let receivedMessage = JSON.parse(message.body);
            readMessage(receivedMessage);
        });
        stompClient.subscribe("/init-chat", function (message) {
            let receivedMessage = JSON.parse(message.body);
            receivedMessage.forEach(receivedMessage => {
                readMessage(receivedMessage);
            });
        })
    })

    function readMessage(receivedMessage){
        let parentDiv = document.createElement("div");
        parentDiv.classList.add("pb-4");
        if (receivedMessage.sender === currentUsername) parentDiv.classList.add("chat-message-right");
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
        senderDiv.textContent = receivedMessage.sender;
        let messageContent = document.createElement("p");
        messageContent.textContent = receivedMessage.content;
        messageDiv.appendChild(senderDiv)
        messageDiv.appendChild(messageContent);


        parentDiv.appendChild(avatarDiv);
        parentDiv.appendChild(messageDiv);
        if (messageNotExisted[receivedMessage.id] === undefined) {
            messageNotExisted[receivedMessage.id] = "defined";
            messages.appendChild(parentDiv);
        }
    }
})
