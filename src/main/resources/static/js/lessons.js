function addLessons(){
    var number = document.getElementById("lesson").value;
    var container = document.getElementById("container");
    while (container.hasChildNodes()) {
        container.removeChild(container.lastChild);
    }
    for (let i=0;i<number;i++){
        container.appendChild(document.createTextNode("Lesson " + (i+1) + " "));
        var input = document.createElement("input");
        input.type = "file";
        input.name = "files";
        container.appendChild(input);
        container.appendChild(document.createElement("br"));
    }
};

function enableTab(id) {
    var el = document.getElementById(id);
    el.onkeydown = function(e) {
        if (e.keyCode === 9) { // tab was pressed

            // get caret position/selection
            var val = this.value,
                start = this.selectionStart,
                end = this.selectionEnd;

            // set textarea value to: text before caret + tab + text after caret
            this.value = val.substring(0, start) + '\t' + val.substring(end);

            // put caret at right position again
            this.selectionStart = this.selectionEnd = start + 1;

            // prevent the focus lose
            return false;

        }
    };
}

// Enable the tab character onkeypress (onkeydown) inside textarea...
// ... for a textarea that has an `id="my-textarea"`
enableTab('textareaNews')