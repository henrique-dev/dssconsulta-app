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
        <h1>Perfil do médico</h1>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <table border="1">
            <th>id</th>
            <th>name</th>   
            <th>workinfo</th>   
            <th>expage</th>   
            <th>evaluationavg</th>   
            <th>evaluationcount</th>   
            <th>bio</th>   
            <th>infocompl</th>   
            <tr>
                <td>${medicProfile.id}</td>
                <td>${medicProfile.medicName}</td>                
                <td>
                    <select>
                        <c:forEach items="${medicProfile.medicWorkAddressList}" var="medicWorkAddress">
                            <option value="${medicWorkAddress.medicWorkAddressId}">
                                {workaddressid:${medicWorkAddress.medicWorkAddressId}},
                                {clinicid:${medicWorkAddress.clinicProfile.id}},
                                {clinicname:${medicWorkAddress.clinicProfile.clinicName}},
                                {specialityid:${medicWorkAddress.medicSpeciality.speciality.specialityId}},
                                {specialityname:${medicWorkAddress.medicSpeciality.speciality.specialityName}}
                            </option>
                        </c:forEach>
                    </select>                        
                </td>
                <td>${medicProfile.medicProfileExpAge}</td>
                <td>${medicProfile.medicProfileEvaluationAvg}</td>
                <td>${medicProfile.medicProfileEvaluationCount}</td>
                <td>${medicProfile.medicProfileBio}</td>
                <td>${medicProfile.medicProfileInfoCompl}</td>
            </tr>
        </table>
    </body>
</html>
