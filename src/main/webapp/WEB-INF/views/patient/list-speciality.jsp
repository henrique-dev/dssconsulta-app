<%-- 
    Document   : speciality-list
    Created on : 22/08/2018, 14:41:11
    Author     : Paulo Henrique Gonçalves Bacelar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de marcação de consulta</title>
    </head>
    <body>
        <h1>Listar especialidades</h1>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <table border="1">
            <th>specialityId</th>
            <th>specialityName</th>            
            <c:forEach items="${specialityList}" var="speciality">
                <tr>
                <td>${speciality.specialityId}</td>
                <td>${speciality.specialityName}</td>                
            </tr>
            </c:forEach>            
        </table>
    </body>
</html>