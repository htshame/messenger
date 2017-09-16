<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <head>
        <title>Registration Page</title>
        <script src="<c:url value="/resources/js/lib/jquery-3.1.1.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/lib/jquery-ui.min.css"/>">
        <script src="<c:url value="/resources/js/lib/jquery-ui.min.js"/>"></script>
        <script src="<c:url value="/resources/js/register.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/register.css"/>">
    </head>
    <body>
        <div class="header-class">
            <h1>This is the registration page!</h1>
            <h3>No further moving-button tricks &#9786;</h3>
        </div>
        <div class="register-registration-box">
            <h3 class="register-registration-box-header">Sign up here!</h3>
            <table>
                <tr>
                    <td>Login:</td>
                    <td>
                        <input type='text' id='username'>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <input type='text' id='email'>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <input type='password' id='password' />
                    </td>
                </tr>
                <tr>
                    <td>Your Name:</td>
                    <td>
                        <input type='text' id='name' />
                    </td>
                </tr>
                <tr>
                    <td colspan='2'>
                        <input id="sign-up" type="button" onclick="register()" value="Sign up" class="register-submit-button"/>
                    </td>
                </tr>
            </table>
            <a href="/messenger/">
                <button class="register-get-back-button">
                    &#8592; Get back
                </button>
            </a>
        </div>
        <div id="dialog-success" title="Success" style="display: none">
            <p>Let's say, you are registered now. You may log in on the login page.</p>
        </div>
        <div id="dialog-error" title="Error" style="display: none">
            <p>User already exists.</p>
        </div>
    </body>
</html>
