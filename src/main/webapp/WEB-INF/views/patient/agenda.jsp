<%-- 
    Document   : agenda
    Created on : 21/08/2018, 16:59:47
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
        <h1>Minha agenda</h1>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <table border="1">
            <th>consultId</th>
            <th>consultCreationDate</th>
            <th>consultForDate</th>
            <th>consultConsulted</th>
            <th>medicProfileId</th>
            <th>specialityId</th>
            <th>medicWorkAddressId</th>
            <c:forEach items="${consultList}" var="consult">
                <tr>
                    <td>${consult.consultId}</td>
                    <td>${consult.consultCreationDate}</td>
                    <td>${consult.consultForDate}</td>
                    <td>${consult.consultConsulted}</td>
                    <td>${consult.medicSpeciality.medicProfile.id}</td>
                    <td>${consult.medicSpeciality.speciality.specialityId}</td>
                    <td>${consult.medicWorkAddress.medicWorkAddressId}</td>
                </tr>
            </c:forEach>                        
        </table>
    </body>
</html>
