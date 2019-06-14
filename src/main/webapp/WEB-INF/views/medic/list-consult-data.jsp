<%-- 
    Document   : list-consult-data
    Created on : 25/09/2018, 14:20:44
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(consultList:
<c:forEach items="${consultList}" var="consult" varStatus="i">
    [consult:
        {consultId:${consult.consultId}},
        {consultForDate:${consult.consultForDate.time}},
        {patientId:${consult.patientProfile.id}},
        {patientName:${consult.patientProfile.patientName}}
        <c:if test="${consult.patientProfile.file != null}">
            ,
            {imageId:${consult.patientProfile.file.fileId}},
            {imageLength:${consult.patientProfile.file.fileLength}}
        </c:if>
    ]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>)