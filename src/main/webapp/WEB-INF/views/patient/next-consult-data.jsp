<%-- 
    Document   : next-consult-data
    Created on : 03/09/2018, 09:05:05
    Author     : Paulo Henrique Gonçalves Bacelar
--%>

<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(list:
[medicProfile:
    {id:${medicProfile.id}},
    {name:${medicProfile.medicName}},
    {currentDate:${currentDate}},
    (medicWorkAddressList:
    <c:forEach items="${medicProfile.medicWorkAddressList}" var="workAddress" varStatus="i">
        [workAddress:
            {id:${workAddress.medicWorkAddressId}},
            {dateLast:${workAddress.medicWorkScheduling.medicWorkSchedulingDateLast.time}},
            {daysOfWeek:${workAddress.medicWorkScheduling.medicWorkSchedulingDaysOfWeek}},
            {counterOfDay:${workAddress.medicWorkScheduling.medicWorkSchedulingCounterOfDay}},
            {perDay:${workAddress.medicWorkScheduling.medicWorkSchedulingPerDay}},
            {clinicName:${workAddress.clinicProfile.clinicName}},
            {specialityId:${workAddress.medicSpeciality.speciality.specialityId}},
            {specialityName:${workAddress.medicSpeciality.speciality.specialityName}},
            {specialityPriv:${workAddress.medicSpeciality.speciality.specialityPriv}}
        ]
        <c:if test="${!i.last}">
        ,
        </c:if>
    </c:forEach>
    )
    <c:if test="${medicProfile.file != null}">
        ,
        {imageId:${medicProfile.file.fileId}},
        {imageLength:${medicProfile.file.fileLength}}
    </c:if>
]
)