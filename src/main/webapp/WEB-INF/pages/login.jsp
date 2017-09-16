<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <head>
        <title>Login Page</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
    </head>
    <body onload='document.loginForm.username.focus();'>
        <div class="header-class">
            <h1>This is the login page!</h1>
            <h3>No further moving-button tricks &#9786;</h3>
        </div>
        <div id="login-box" class="login-box-class">
            <h3 class="login-box-header">Enter your login and password</h3>
            <c:if test="${not empty error}">
                <div class="login-error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="login-msg">${msg}</div>
            </c:if>

            <form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td>
                            <input type='text' name='username'>
                        </td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td>
                            <input type='password' name='password' />
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2'>
                            <input name="submit" type="submit" value="Login" class="login-submit-button"/>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </div>
        <a href="/messenger/">
            <button class="login-get-back-button">
                &#8592; Get back
            </button>
        </a>
    </body>
</html>
