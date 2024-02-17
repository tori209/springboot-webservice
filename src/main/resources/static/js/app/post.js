window.onload = () => {
    findAllFilesById();
}

async function findAllFilesById() {
    const reqUrl = location.pathname + '/files';
    const response = await $.getJSON(reqUrl);

    if (!response.length) {
        return false;
    }

    let htmlFileList = "";
    response.forEach(row => {
        htmlFileList += `<a class="list-group-item list-group-item-action"> ${row.realName} </a>`
    })
    htmlFileList = `<div class="list-group">` + htmlFileList + `</div>`;
    document.getElementById("file-container").innerHTML = htmlFileList;
}