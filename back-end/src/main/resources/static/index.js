    function updatePost(event) {
        let button = event.target;
        let postId = button.getAttribute("data-post-id");
        let currentContent = button.getAttribute("data-post-content");
        let postElement = document.getElementById('content-' + postId);
        console.log(postElement.content);
        postElement.innerHTML = `
            <form th:action="@{'update-post/' + ${postId}}" method="post">
                <input type="text" name="updatedContent" value="${currentContent}" />
                <button type="submit">Update</button>
            </form>
        `;
}