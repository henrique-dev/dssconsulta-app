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
        <h1>Meu perfil</h1>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <table border="1">
            <th>id</th>
            <th>name</th>            
            <th>cpf</th>
            <th>email</th>
            <th>genre</th>
            <th>birthdate</th>
            <th>height</th>
            <th>bloodtype</th>
            <th>telephone</th>
            <tr>
                <td>${patientProfile.id}</td>
                <td>${patientProfile.patientName}</td>
                <td>${patientProfile.patientCpf}</td>
                <td>${patientProfile.patientProfileEmail}</td>
                <td>${patientProfile.patientProfileGenre}</td>
                <td>${patientProfile.patientProfileBirthDate}</td>
                <td>${patientProfile.patientProfileHeight}</td>
                <td>${patientProfile.patientProfileBloodType}</td>
                <td>${patientProfile.patientProfileTelephone}</td>
            </tr>
        </table>
    </body>
</html>
