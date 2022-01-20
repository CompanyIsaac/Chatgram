<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<body>
<%
    if(session.getAttribute("userId")==null){
        response.sendRedirect("index.jsp");
    }
%>

<div class="container-fluid w-50 p-3 mx-auto">
    <div class="d-flex justify-content-center">
        <form action="listchat" method="post" style="margin: 20px;">
            <div class="form-group gap-5">
                <label for="searchedChatName">Search chat by name</label>
                <input type="text" class="form-control" id="searchedChatName" name="searchedChatName"/>
            </div>
            <button class="btn btn-primary" style="margin-bottom: 20px">List chats</button>
        </form>
        <form action="listchat" method="post" style="margin: 20px;">
            <div class="form-group gap-5">
                <label for="searchedChatCategory">Search chat by category</label>
                <input type="text" class="form-control" id="searchedChatCategory" name="searchedChatCategory"/>
            </div>
            <button class="btn btn-primary" style="margin-bottom: 20px">List chats</button>
        </form>
    </div>
    <div class="d-flex justify-content-center">
        <form action="listuser" method="post" style="margin: 20px;">
            <div class="form-group gap-5">
                <label for="searchedUserName">Search user by name</label>
                <input type="text" class="form-control" id="searchedUserName" name="searchedUserName"/>
            </div>
            <button class="btn btn-primary" style="margin-bottom: 20px">List users</button>
        </form>
        <form action="listuser" method="post" style="margin: 20px;">
            <div class="form-group gap-5">
                <label for="searchedUserInterest">Search user by interest</label>
                <input type="text" class="form-control" id="searchedUserInterest" name="searchedUserInterest"/>
            </div>
            <button class="btn btn-primary" style="margin-bottom: 20px">List users</button>
        </form>
    </div>
    <form action="createchat" method="post">
        <div class="form-group">
            <label for="chatName">Chat name</label>
            <input type="text" class="form-control" id="chatName" name="chatName" required/>
        </div>
        <div class="form-group">
            <label for="rules">Chat rules</label>
            <input type="text" class="form-control" id="rules" name="rules" required/>
        </div>
        <div class="form-group">
            <label for="category">Chat category</label>
            <input type="text" class="form-control" id="category" name="category" required/>
        </div>
        <button class="btn btn-primary">Create chat</button>
    </form>
    <form action="logout">
        <div class="form-groupr">
            <button class="btn btn-primary">Logout</button>
        </div>
    </form>
</div>

</body>
</html>
