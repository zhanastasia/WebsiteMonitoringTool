function playSoundCritical() {
    var sound = document.getElementById("audioCritical");
    sound.play();
}

function playSoundWarning() {
    var sound = document.getElementById("audioWarning");
    sound.play();
}

(function () {
    window.onload = function () {
        var tableDatas = document.getElementsByClassName("status");

        for (var i = 0; i < tableDatas.length; i++) {
            switch (tableDatas[i].innerText) {
                case "CRITICAL":
                    tableDatas[i].style.background='#af362e';
                    playSoundCritical();
                    break;
                case "WARNING":
                    tableDatas[i].style.background='#bf8724';
                    playSoundWarning();
                    break;
                case "OK":
                    tableDatas[i].style.background='#4caf50';
                    break;
            }
        }
    };
})();

function startMonitoring() {
    var value = "<%=monitoringPeriod%>";
    setTimeout(function(){
        window.location.reload();
    }, value*100); //seconds
}