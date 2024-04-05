let posts = document.querySelectorAll(".post");
let userId = parseInt(document.querySelector(".current-user-id").textContent);
let timeOutId;
posts.forEach(function (post) {
    let postReacted = false;
    let likeButton = post.querySelector(".like-button");
    let postIdElement = post.querySelector(".post-id");
    let reactionIcon = post.querySelector(".reaction-icon");
    let numberReaction = post.querySelectorAll(".number-reaction");
    if (postIdElement === null) return
    let postId = parseInt(postIdElement.textContent);
    if (likeButton) {
        let allReaction = post.querySelector(".all-reaction");
        let allReactionButton = post.querySelectorAll(".emoji-button");
        likeButton.addEventListener("mouseover", function () {
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

        allReactionButton.forEach(function (button) {
            button.addEventListener("click", function () {

                allReaction.style.display = "none";
                let reactionType;
                let imgUrl;

                if (postReacted) {
                    postReacted = false;
                    numberReaction--;
                    reactionIcon.setAttribute("src", "/icon/hand-thumbs-up.svg");
                    fetch("/posts/" + postId + "/reactions", {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({userId: userId})
                    }).then(r => {});
                } else {
                    postReacted = true;
                    numberReaction++;
                    if (button.classList.contains("smaller-like-button")) {
                        reactionType = 'LIKE';
                        imgUrl = '/icon/hand-thumbs-up-fill.svg';
                    } else if (button.classList.contains("love-button")) {
                        reactionType = 'LOVE';
                        imgUrl = '/icon/heart-fill.svg';
                    } else if (button.classList.contains("haha-button")) {
                        reactionType = 'HAHA';
                        imgUrl = '/icon/emoji-laughing-fill.svg';
                    } else if (button.classList.contains("sad-button")) {
                        reactionType = 'SAD';
                        imgUrl = '/icon/emoji-tear-fill.svg';
                    } else if (button.classList.contains("wow-button")) {
                        reactionType = 'WOW';
                        imgUrl = '/icon/emoji-surprise-fill.svg';
                    } else if (button.classList.contains("angry-button")) {
                        reactionType = 'ANGRY';
                        imgUrl = '/icon/emoji-angry-fill.svg';
                    }


                    reactionIcon.setAttribute("src", imgUrl);
                    let reaction = {
                        type: reactionType,
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
                    });
                }
            })
        })


        likeButton.addEventListener("click", function () {
            if (postReacted) {
                postReacted = false;
                numberReaction--;
                reactionIcon.setAttribute("src", "/icon/hand-thumbs-up.svg");
            }
            else {
                postReacted = true;
                numberReaction++;
                reactionIcon.setAttribute("src", "/icon/hand-thumbs-up-fill.svg")
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
                })
            }
        });

    }
})
