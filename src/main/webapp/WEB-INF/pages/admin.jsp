<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <head>
        <title>Messenger Admin Page</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/admin.css"/>">
    </head>
    <body>
        <div class="header-class">
            <h1>${title}</h1>
            <h1>${message}</h1>
        </div>

        <c:url value="/j_spring_security_logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <script src="<c:url value="/resources/js/lib/jquery-3.1.1.min.js"/>"></script>
            <link rel="stylesheet" href="<c:url value="/resources/css/lib/jquery-ui.min.css"/>">
            <link rel="stylesheet" href="<c:url value="/resources/css/lib/select2.css"/>">
            <script src="<c:url value="/resources/js/lib/jquery-ui.min.js"/>"></script>
            <script src="<c:url value="/resources/js/lib/select2.min.js"/>"></script>
            <script src="<c:url value="/resources/js/admin.js"/>"></script>
            <h3 class="hello-logged-in-admin-principal">
                Welcome, ${pageContext.request.userPrincipal.name} | <a
                    href="javascript:formSubmit()"> Logout</a>
            </h3>
            <div>
                <a href="/messenger/welcome">
                    <button class="get-back-from-admin-button">
                        Get back
                    </button>
                </a>
            </div>
            <div class="table-div">
                <table>
                    <tr>
                        <td>
                            <div class="all-users-select2-tag">
                                <p>All users:</p>
                            </div>
                        </td>
                        <td>
                            <div class="all-users-select2">
                                <input id="all-users" style="width:300px" >
                            </div>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <button id="delete-user" class="delete-user-class">Delete</button>
                        </td>
                        <td>
                            <button id="make-admin" class="make-user-admin">Add Admin role</button>
                        </td>
                        <td>
                            <button id="revert-admin" class="roll-admin-back">Revert Admin role</button>
                        </td>
                    </tr>
                    <tr>
                        <td/>
                        <td>
                            <button id="add-new-user-button" class="add-new-user-button-class">Add new user</button>
                        </td>
                        <td/>
                    </tr>
                </table>
            </div>
            <div id="choose-contact" title="Warning!" style="display: none">
                <p>Choose contact!</p>
            </div>
            <div id="info-incomplete" title="Error!" style="display: none">
                <p>Login, email and password must ba specified!</p>
            </div>
            <div id="user-added" title="Success!" style="display: none">
                <p>User added successfully!</p>
            </div>
            <div id="add-new-user" title="Adding new user" style="display: none">
                <div class="add-new-user-table-div">
                    <table>
                        <tr>
                            <td>
                                <div class="">
                                    <p>Login:</p>
                                </div>
                            </td>
                            <td>
                                <div class="">
                                    <input id="add-user-login">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="">
                                    <p>Email:</p>
                                </div>
                            </td>
                            <td>
                                <div class="">
                                    <input id="add-user-email">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="">
                                    <p>Password:</p>
                                </div>
                            </td>
                            <td>
                                <div class="">
                                    <input id="add-user-password" type="password">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="">
                                    <p>Name:</p>
                                </div>
                            </td>
                            <td>
                                <div class="">
                                    <input id="add-user-name">
                                </div>
                            </td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td/>
                            <td>
                                <button id="add-user-actual" class="make-user-admin">Add user</button>
                            </td>
                            <td/>
                        </tr>
                    </table>
                </div>
            </div>
        </c:if>
    </body>
</html>
