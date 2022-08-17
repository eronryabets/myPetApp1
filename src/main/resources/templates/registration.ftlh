<head>
    <h5>Registration page</h5>
</head>

<div>
    <form method="post">
        <div> Введите логин</div>
        <input type="text" name="login" placeholder="login"/>


        <div> Введите имя</div>
        <input type="text" name="name" placeholder="name"/>

        <div> Введите фамилию</div>
        <input type="text" name="surname" placeholder="surname"/>

        <div> Введите пароль</div>
        <input type="text" name="password" placeholder="password"/>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <br>
        <br>
        <button type="submit">Add user</button>
    </form>
</div>