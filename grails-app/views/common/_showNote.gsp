<tr noteDiv="${noteInstance.id}">
    <td noteId="${noteInstance.id}">
        ${noteInstance.body}
    </td>
    <td>
        <g:if test="${noteInstance.lastUpdated == noteInstance.dateCreated}">
            Posted by ${noteInstance.poster} on
        </g:if>
        <g:else>
            Updated by ${noteInstance.poster} on
        </g:else>
        <enrollio:formatDate date="${noteInstance.lastUpdated}" />
        <a href="#" class="editNote" editNoteId="${noteInstance.id}">Edit</a>&nbsp;
        <a href="#" class="saveNote" saveNoteId="${noteInstance.id}" style="display:none">Save</a>&nbsp;
        <a href="#" class="cancelNote" cancelNoteId="${noteInstance.id}" style="display:none">Cancel</a>
        <input type="hidden" origNoteText="${noteInstance.id}" value="${noteInstance.body}" />
    </td>
</tr
