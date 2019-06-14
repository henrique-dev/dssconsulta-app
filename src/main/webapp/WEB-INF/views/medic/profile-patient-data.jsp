<%-- 
    Document   : profile-patient-data
    Created on : 27/09/2018, 12:49:51
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
(patientProfile:
    [patient:        
        {patientId:${patientProfile.id}},
        {patientName:${patientProfile.patientName}},
        {currentDate:${currentDate.time}},
        {patientHeight:${patientProfile.patientProfileHeight}},
        {patientWeight:${patientProfile.patientProfileWeight}},
        {patientBirthDate:${patientProfile.patientProfileBirthDate.time}},
        {patientBloodType:${patientProfile.patientProfileBloodType}}
        <c:if test="${patientProfile.file != null}">
            ,
            {imageId:${patientProfile.file.fileId}},
            {imageLength:${patientProfile.file.fileLength}}
        </c:if>
    ]
)