<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<body>
<div class="container-fluid d-flex justify-content-center">
    <form action="registration" method="post" style="margin: 50px">
        <div class="form-group" >
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp" placeholder="Enter name" required>
        </div>
        <div class="form-group" >
            <label for="email">Email</label>
            <input type="text" class="form-control" pattern="[^@\s]+@[^@\s]+\.[^@\s]+" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required placeholder="Password">
        </div>
        <div class="form-group">
            <label for="gender">You are a</label>
            <select multiple class="form-control" id="gender">
                <option>Male</option>
                <option>Female</option>
                <option>Other</option>
            </select>
        </div>
        <div class="form-group">
            <label for="interested">Interested in</label>
            <input type="text" class="form-control" id="interested" name="interested" required placeholder="I'm interested in...">
        </div>
        <div class="form-group">
            <label for="birthday">Birthday</label>
            <input type="date" class="form-control" id="birthday" name="birthday"value="2000-01-01" min="1900-01-01" max="2008-01-01">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <button style="float: right;" class="btn btn-primary" onclick="window.location.href='login'">Cancel</button>
    </form>
</div>
</body>
</html>