<%-- 
    Document   : agenda-data
    Created on : 31/08/2018, 15:27:11
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(consultList:<c:forEach items="${consultList}" var="consult" varStatus="i">
    [consult:
        {id:${consult.consultId}},
        {forDate:${consult.consultForDate}},
        {medic:${consult.medicSpeciality.medicProfile.medicName}},
        {speciality:${consult.medicSpeciality.speciality.specialityName}}
    ]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>)
