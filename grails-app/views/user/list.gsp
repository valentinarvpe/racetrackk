<%--
  Created by IntelliJ IDEA.
  User: valentina
  Date: 1/11/24
  Time: 23:35
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<g:each in="${userInstanceList}"
        status="i"
        var="userInstance">
    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
        <td>
            <g:link action="show" id="${userInstance.id}">
                ${fieldValue(bean:userInstance, field:'id')}
            </g:link>
        </td>
        <td>${fieldValue(bean:userInstance,
                field:'login')}</td>
        <td>${fieldValue(bean:userInstance,
                field:'password')}</td>
        <td>${fieldValue(bean:userInstance,
                field:'role')}</td>
    </tr>
    <div class="paginateButtons">
        <g:paginate total="${userInstanceTotal}" />
    </div>
</g:each>
</body>
</html>