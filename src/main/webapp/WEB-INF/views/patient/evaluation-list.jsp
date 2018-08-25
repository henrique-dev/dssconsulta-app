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
        <h1>Minhas avaliações</h1>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <table border="1">
            <th>evaluationId</th>
            <th>medicId</th>
            <th>medicName</th>
            <th>evaluationDescName</th>
            <th>evaluationDescInfo</th>
            <th>evaluationScore</th>
            <c:forEach items="${evaluationList}" var="evaluation">
                <tr>
                <td>${evaluation.evaluationId}</td>
                <td>${evaluation.medicProfile.id}</td>
                <td>${evaluation.medicProfile.medicName}</td>
                <td>${evaluation.evaluationDescName}</td>
                <td>${evaluation.evaluationDescInfo}</td>
                <td>${evaluation.evaluationScore}</td>
            </tr>
            </c:forEach>            
        </table>
    </body>
</html>