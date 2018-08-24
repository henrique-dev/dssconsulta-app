<%-- 
    Document   : medic-list
    Created on : 23/08/2018, 09:36:53
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
        <h1>Perfil da clinica</h1>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <table border="1">
            <th>clinicId</th>
            <th>clinicName</th>   
            <th>medicWorkInfo</th>   
            <th>clinicCnpj</th>   
            <th>clinicProfileAddress</th>               
            <tr>
                <td>${clinicProfile.id}</td>
                <td>${clinicProfile.clinicName}</td>                
                <td>
                    <select>
                        <c:forEach items="${clinicProfile.clinicTelephoneList}" var="clinicTelephone">
                            <option value="${clinicTelephone.clinicTelephoneNumber}">
                                {${clinicTelephone.clinicTelephoneNumber}},                                
                            </option>
                        </c:forEach>
                    </select>                        
                </td>
                <td>${clinicProfile.clinicCnpj}</td>
                <td>${clinicProfile.clinicProfileAddress}</td>                
            </tr>
        </table>
    </body>
</html>
