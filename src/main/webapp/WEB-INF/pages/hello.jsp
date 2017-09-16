<%@taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Messenger Welcome Page</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/hello.css"/>">
    </head>
    <body>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <div class="header-class">
                <h1>${head}</h1>
                <h1>${description}</h1>
            </div>
            <table class="hello-login-sing-up-table">
                <tr>
                    <td>
                        <a href="/messenger/login/">
                            <button class="hello-login-button" id="login">
                                Login
                            </button>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/messenger/reg/">
                            <button class="hello-sign-up-button" id="sign-up">
                                Sign up
                            </button>
                        </a>
                    </td>
                </tr>
            </table>
        </c:if>

        <sec:authorize access="hasRole('ROLE_USER')">
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
                <link rel="stylesheet" href="<c:url value="/resources/css/lib/jquery-ui.min.css"/>">
                <link rel="stylesheet" href="<c:url value="/resources/css/lib/select2.css"/>">
                <link rel="stylesheet" href="<c:url value="/resources/css/lib/slickgrid/slick.grid.css"/>">
                <link rel="stylesheet" href="<c:url value="/resources/css/lib/slickgrid/jquery-ui-1.8.16.custom.css"/>">

                <script src="<c:url value="/resources/js/lib/slickgrid/firebugx.js"/>"></script>
                <script src="<c:url value="/resources/js/lib/slickgrid/jquery-1.7.min.js"/>"></script>
                <script src="<c:url value="/resources/js/lib/jquery-ui.min.js"/>"></script>
                <script src="<c:url value="/resources/js/lib/slickgrid/jquery.event.drag-2.2.js"/>"></script>
                <script src="<c:url value="/resources/js/lib/slickgrid/jquery.jsonp-2.4.min.js"/>"></script>
                <script src="<c:url value="/resources/js/lib/slickgrid/slick.core.js"/>"></script>
                <script src="<c:url value="/resources/js/lib/slickgrid/slick.remotemodel.js"/>"></script>
                <script src="<c:url value="/resources/js/lib/slickgrid/slick.grid.js"/>"></script>
                <script src="<c:url value="/resources/js/lib/select2.min.js"/>"></script>
                <sec:authorize ifNotGranted="ROLE_ADMIN">
                    <script src="<c:url value="/resources/js/helloSlick.js"/>"></script>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <script src="<c:url value="/resources/js/helloSlickAdmin.js"/>"></script>
                </sec:authorize>
                <script src="<c:url value="/resources/js/hello.js"/>"></script>

                <h3 class="hello-logged-in-user-principal">
                    You are logged in as: ${pageContext.request.userPrincipal.name} | <a
                        href="javascript:formSubmit()"> Logout</a>
                </h3>
                <h2 class="hello-authed-user-header"> Here are your inbox messages </h2>
                <button class="hello-authed-open-contacts" id="open-contacts">
                    Contacts
                </button>
                <button class="hello-authed-change-password" id="change-password">
                    Change Password
                </button>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="go-to-admin-page-button">
                        <a href="/messenger/admin">
                            <button class="admin-page-button-actual">
                                Admin page
                            </button>
                        </a>
                    </div>
                </sec:authorize>
                <div class="message-grid-div">
                    <div id="myGrid" style="width:100%;height:600px;"></div>
                </div>
                <div id="dialog-contacts" title="Contacts" style="display: none;" class="hello-authed-contacts-box">
                    <table>
                        <tr>
                            <td>
                                <p>All contacts:</p>
                            </td>
                            <td>
                                <input id="all-contacts" style="width:300px" >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Your contacts:</p>
                            </td>
                            <td>
                                <input id="my-contacts" style="width:300px">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button id="add-contact" class="add-contact-class">Add</button>
                            </td>
                            <td>
                                <button id="delete-contact" class="delete-contact-class">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td/>
                            <td>
                                <button id="send-message" class="send-message-class">Send</button>
                            </td>
                            <td/>
                        </tr>
                    </table>
                </div>
                <div id="dialog-password" title="Change password" style="display: none">
                    <h3>New password</h3>
                    <input id="new-password" type="password">
                    <br/>
                    <h3>Confirm new password</h3>
                    <input id="conf-new-password" type="password">
                    <button id="user-change-password" class="hello-authed-change-pw-button">Change</button>
                </div>
                <div id="dialog-message-read" title="Message" style="display: none">
                    <h3>Sender:</h3>
                    <input id="mess-sender" type="text">
                    <br/>
                    <h3>Date:</h3>
                    <input id="mess-date" type="text">
                    <h3>Theme:</h3>
                    <input id="mess-theme" type="text">
                    <h3>Message:</h3>
                    <input id="mess-text" type="text">
                    <button id="delete-this-message" class="hello-authed-change-pw-button">Delete</button>
                </div>
                <div id="password-wrong" title="Error" style="display: none">
                    <p>Passwords don't match</p>
                </div>
                <div id="password-changed" title="Success" style="display: none">
                    <p>Password is changed!</p>
                </div>
                <div id="choose-contact" title="Warning!" style="display: none">
                    <p>Choose contact!</p>
                </div>
                <div id="message-sent" title="Success" style="display: none">
                    <p>Message sent!</p>
                </div>
                <div id="dialog-send-message" title="Send message" style="display: none">
                    <table>
                        <tr>
                            <td>
                                <p>Choose addressee:</p>
                            </td>
                            <td>
                                <input id="my-contacts-send" style="width:300px" >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Theme:</p>
                            </td>
                            <td>
                                <input id="my-theme" type="text" style="width:300px" >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>Message text:</p>
                            </td>
                            <td>
                                <input id="my-text" type="text" style="width:300px" >
                            </td>
                        </tr>
                        <tr>
                            <td/>
                            <td>
                                <button id="send-message-actual" class="send-message-class">Send message</button>
                            </td>
                            <td/>
                        </tr>
                    </table>
                </div>
            </c:if>
        </sec:authorize>
    </body>
</html>
