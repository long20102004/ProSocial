var socket = new WebSocket("ws://localhost:8080/stomp");
var stompClient = Stomp.over(socket);
var myMessage = document.querySelector(".my-list-message");
var sendButton = document.querySelector(".send-message-button");
var inputField = document.querySelector(".input-message");
var signInButton = document.querySelector(".sign-in-button");
var userNameField = document.querySelector(".input-name");
var containerDiv = document.querySelector(".container");
var signInDiv = document.querySelector(".sign-in");
var userName;
// containerDiv.style.display = 'none';

// signInButton.addEventListener('click', function(event){
//     event.preventDefault();
//     containerDiv.style.display = 'inline-flex';
//     signInDiv.style.display = 'none';
//     userName = userNameField.value;
    
    

    stompClient.connect({}, function (event) {
        var payload = {
            content : userName + "just joined the chat!",
            sender: userName,
            type: "CHAT"
        };

        stompClient.send("/send-message", {}, JSON.stringify(payload));   

        sendButton.addEventListener('click', function (clickEvent) {
            clickEvent.preventDefault();
            console.log("sent");
            var message = {
                content: inputField.value,
                sender: "Long",
                type: "CHAT"
            };
            stompClient.send("/send-message", {}, JSON.stringify(message));
            console.log(done);
            inputField.value = '';
        });

        stompClient.subscribe("/messages", function (message) {

            var receivedPayload = JSON.parse(message.body);
            var li = document.createElement("li");
            li.textContent = receivedPayload.body;

            myMessage.appendChild(li);
    });
}
, function(error){
    console.log("Error")
}); 
// });
