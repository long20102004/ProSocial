let posts = document.querySelectorAll(".post");
let userId = parseInt(document.querySelector(".current-user-id").textContent);
let reactionIcon = document.querySelector(".reaction-icon");
let timeOutId;
posts.forEach(function (post) {
    let likeButton = post.querySelector(".like-button");
    let postIdElement = post.querySelector(".post-id");
    console.log(postIdElement);
    if (postIdElement === null) return
    let postId = parseInt(postIdElement.textContent);
    if (likeButton) {
        let allReaction = post.querySelector(".all-reaction");
        let likeButtonSmaller = allReaction.querySelector(".smaller-like-button");
        let loveButton = allReaction.querySelector(".love-button");
        let hahaButton = allReaction.querySelector(".haha-button");
        let wowButton = allReaction.querySelector(".wow-button");
        let sadButton = allReaction.querySelector(".sad-button");
        let angryButton = allReaction.querySelector(".angry-button");
        likeButton.addEventListener("mouseover", function (){
            clearTimeout(timeOutId);
            timeOutId = setTimeout(function () {
                allReaction.style.display = "inline-flex";
            }, 800);
        });
        post.addEventListener("mouseout", function () {
            clearTimeout(timeOutId);
            timeOutId = setTimeout(function () {
                let allReaction = post.querySelector(".all-reaction");
                allReaction.style.display = "none";
            }, 800);
        });
        likeButton.addEventListener("click", function () {
            let reaction = {
                type: 'LIKE',
                postId: postId,
                count: 1,
                userId: userId
            }
            fetch("/posts/" + postId, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reaction)
            }).then(r => {
                reactionIcon.setAttribute("src", "/icon/hand-thumbs-up-fill.svg")
            })
        });
        likeButtonSmaller.addEventListener("click", function () {
            let reaction = {
                type: 'LIKE',
                postId: postId,
                count: 1,
                userId: userId
            }
            fetch("/posts/" + postId, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reaction)
            }).then(r => {
                reactionIcon.setAttribute("src", "/icon/hand-thumbs-up-fill.svg")
            })
        });

        loveButton.addEventListener("click", function (){
            let reaction = {
                type: 'LOVE',
                postId: postId,
                count: 1,
                userId: userId
            }
            fetch("/posts/" + postId, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reaction)
            }).then(r => reactionIcon.setAttribute("src", "/icon/heart-fill.svg"))
        });
        hahaButton.addEventListener("click", function (){
            let reaction = {
                type: 'HAHA',
                postId: postId,
                count: 1,
                userId: userId
            }
            fetch("/posts/" + postId, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reaction)
            }).then(r => reactionIcon.setAttribute("src", "/icon/emoji-laughing-fill.svg"))
        });
        sadButton.addEventListener("click", function (){
            let reaction = {
                type: 'SAD',
                postId: postId,
                count: 1,
                userId: userId
            }
            fetch("/posts/" + postId, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reaction)
            }).then(r => reactionIcon.setAttribute("src", "/icon/emoji-tear-fill.svg"))
        });
        wowButton.addEventListener("click", function (){
            let reaction = {
                type: 'WOW',
                postId: postId,
                count: 1,
                userId: userId
            }
            fetch("/posts/" + postId, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reaction)
            }).then(r => reactionIcon.setAttribute("src", "/icon/emoji-surprise-fill.svg"))
        });
        angryButton.addEventListener("click", function (){
            let reaction = {
                type: 'ANGRY',
                postId: postId,
                count: 1,
                userId: userId
            }
            fetch("/posts/" + postId, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(reaction)
            }).then(r => reactionIcon.setAttribute("src", "/icon/emoji-angry-fill.svg"))
        })
    }
});