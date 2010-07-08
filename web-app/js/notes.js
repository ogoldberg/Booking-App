 $(".editNote").click(function() {
                      var noteId = $( this ).attr( 'editNoteId' );
                      var noteElement=$( "[noteId=" + noteId + "]");
                      var noteText = noteElement.text().trim();
                      
                      noteElement.html(
                          '<input noteTextId="' + noteId + '" ' +
                          'value="' + noteText + '" />'
                      );

                      // register an event handler to trap ENTER key
                      // which will send the updated note
                      $('[noteTextId=' + noteId + ']').keydown(function(event) {
                        if (event.keyCode == '13') {
                            $("[[saveNoteId=" + noteId + "]").click();
                            event.preventDefault();
                         }
                      });

                      toggleNoteLinks(noteId);
                      
                      // Don't actually trigger the href
                      return false;
                      
                  });

                  $(".saveNote").click(function() {
                      var noteId = $( this ).attr( 'saveNoteId' );
                      var noteElement=$( "[noteTextId=" + noteId + "]");
                      var noteText = noteElement.val().trim();
                      toggleNoteLinks(noteId);

                      $.ajax({
                          url:'${createLink(controller:"contact", action:"updateNote")}',
                          data:  { 'noteText' : noteText, 'id' : noteId },
                          success: function(msg) {
                              $("[noteDiv=" + noteId + "]").replaceWith(msg)
                          }});
                      // Don't actually trigger the href
                      return false;
                  });

                  $(".cancelNote").click(function() {
                      var noteId = $( this ).attr( 'cancelNoteId' );
                      var noteText = $( "[origNoteText=" + noteId + "]").val().trim();
                      
                      $( "[noteId=" + noteId + "]").text(noteText);

                      toggleNoteLinks(noteId);

                      // Don't actually trigger the href
                      return false;
                  });

                  function toggleNoteLinks(noteId) {
                      $("[editNoteId=" + noteId + "]").toggle();
                      $("[cancelNoteId=" + noteId + "]").toggle();
                      $("[saveNoteId=" + noteId + "]").toggle();
                  }
