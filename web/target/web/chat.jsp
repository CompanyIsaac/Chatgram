<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<body>
<%
    if(session.getAttribute("userId")==null){
        response.sendRedirect("index.jsp");
    }
%>

<div class="container-fluid w-50 p-3 mx-auto">
<form action="messagesend" method="post">
    <div class="form-group">
        <label for="message">Message</label>
        <input type="text" class="form-control" id="message" name="message" required/>
    </div>
    <button class="btn btn-primary">Send</button>
</form>
<form action="leavechat" method="post">
    <button class="btn btn-primary">Leave</button>
</form>
</div>

</body>
</html>
