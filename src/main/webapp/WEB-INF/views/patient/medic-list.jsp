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
        <h1>Listar medicos</h1>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <table border="1">
            <th>medicId</th>
            <th>medicName</th>   
            <th>medicWorkInfo</th>   
            <th>medicProfileExpAge</th>   
            <th>medicProfileEvaluationAvg</th>   
            <th>medicProfileEvaluationCount</th>   
            <c:forEach items="${medicProfileList}" var="medicProfile">
                <tr>
                    <td>${medicProfile.id}</td>
                    <td>${medicProfile.medicName}</td>
                    <td>
                        <select>
                            <c:forEach items="${medicProfile.medicWorkAddressList}" var="medicWorkAddress">
                                <option value="${medicWorkAddress.medicWorkAddressId}">
                                    {${medicWorkAddress.medicWorkAddressId}},
                                    {${medicWorkAddress.clinicProfile.clinicId}},
                                    {${medicWorkAddress.clinicProfile.clinicName}},
                                    {${medicWorkAddress.medicSpeciality.speciality.specialityId}},
                                    {${medicWorkAddress.medicSpeciality.speciality.specialityName}}
                                </option>
                            </c:forEach>
                        </select>                        
                    </td>
                    <td>${medicProfile.medicProfileExpAge}</td>
                    <td>${medicProfile.medicProfileEvaluationAvg}</td>
                    <td>${medicProfile.medicProfileEvaluationCount}</td>
                </tr>
            </c:forEach>            
        </table>
    </body>
</html>
