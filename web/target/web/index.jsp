<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<body>
<div>
    <h1 style="text-align: center; margin: 30px;">Chatgram</h1>
</div>
<div class="container-fluid d-flex justify-content-center">
    <form action="login" method="post">
        <div class="form-group" >
            <label for="email">Email address</label>
            <input type="email" class="form-control" pattern="[^@\s]+@[^@\s]+\.[^@\s]+" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <button class="btn btn-primary" onclick="window.location.href='registration'">Registration</button>
    </form>
</div>
</body>
</html>

