<%-- 
    Document   : consult-data
    Created on : 04/09/2018, 10:53:54
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
(patientConsult:
<c:if test="${consult != null}">
    [consult:
        {consultId:${consult.consultId}},
        {patientId:${consult.patientProfile.id}},
        {patientName:${consult.patientProfile.patientName}},
        {currentDate:${currentDate.time}},
        {patientHeight:${consult.patientProfile.patientProfileHeight}},
        {patientWeight:${consult.patientProfile.patientProfileWeight}},
        {patientBirthDate:${consult.patientProfile.patientProfileBirthDate.time}},
        {patientBloodType:${consult.patientProfile.patientProfileBloodType}}
        <c:if test="${consult.patientProfile.file != null}">
            ,
            {imageId:${consult.patientProfile.file.fileId}},
            {imageLength:${consult.patientProfile.file.fileLength}}
        </c:if>
    ]
</c:if>    
)
