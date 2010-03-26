<tr>
    <td>
        <g:link action="show" id="${booking.id}" controller="booking">${booking}</g:link>
    </td>
    <td>
    %{-- TODO create .activeInterests property on booking,
         so we don't need the g:if interest.active --}%
  
    </td>
    <td>
        <g:link
        title="Edit ${booking}"
        name="editbooking${booking.id}"
        action="edit" controller="booking"
        id="${booking.id}"><img  src="${resource(dir:'images/icons',file:'user_edit.png')}" alt="Edit booking" />
        </g:link>
    </td>
</tr>
