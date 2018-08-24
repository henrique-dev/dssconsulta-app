<%-- 
    Document   : agenda-consulta
    Created on : 22/08/2018, 14:01:28
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
        <h1>Minha consulta</h1>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <table border="1">
            <th>consultId</th>
            <th>consultCreationDate</th>
            <th>consultForDate</th>
            <th>consultConsulted</th>
            <th>medicId</th>
            <th>medicName</th>
            <th>medicCrm</th>
            <th>specialityId</th>
            <th>specialityName</th>
            <th>medicWorkAddressId</th>
            <th>medicWorkAddressComplement</th>
            <th>clinicId</th>
            <th>clinicName</th>
            <th>clinicCnpj</th>

            <tr>
                <td>${consult.consultId}</td>
                <td>${consult.consultCreationDate}</td>
                <td>${consult.consultForDate}</td>
                <td>${consult.consultConsulted}</td>
                <td>${consult.medicSpeciality.medicProfile.id}</td>
                <td>${consult.medicSpeciality.medicProfile.medicName}</td>
                <td>${consult.medicSpeciality.medicProfile.medicCrm}</td>                                
                <td>${consult.medicSpeciality.speciality.specialityId}</td>
                <td>${consult.medicSpeciality.speciality.specialityName}</td>                
                <td>${consult.medicWorkAddress.medicWorkAddressId}</td>                
                <td>${consult.medicWorkAddress.medicWorkAddressComplement}</td>
                <td>${consult.medicWorkAddress.clinicProfile.id}</td>
                <td>${consult.medicWorkAddress.clinicProfile.clinicName}</td>
                <td>${consult.medicWorkAddress.clinicProfile.clinicCnpj}</td>
            </tr>

        </table>
    </body>
</html>