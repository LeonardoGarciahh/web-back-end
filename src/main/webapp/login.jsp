<%--
  Created by IntelliJ IDEA.
  User: leonardo.oliveira7
  Date: 02/09/2022
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./public/login.css" media="screen" />
</head>
<body>
  <form class="form" action="authentication" method="post">
      <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Senac_logo.svg/2560px-Senac_logo.svg.png">

      <h3>Desenvolvimento web</h3>

      <input placeholder="Username" type="text" name="username"/>
      <input placeholder="Password" type="password" name="password"/>

      <div class="checkbox">
          <input type="checkbox" name="keepLogged"/>
          <label>Manter logado</label>
      </div>

      <input type="submit"/>
  </form>
</body>
</html>
