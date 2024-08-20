"use strict"
import {byId, verwijderChildElementenVan} from "./util.js";
const mandje =JSON.parse(sessionStorage.getItem("mandje"))

const tbody = byId("tbody")
verwijderChildElementenVan(tbody)
for(const mandItem of mandje){
    const tr = tbody.insertRow()
    tr.insertCell().textContent = mandItem.titel
    tr.insertCell().textContent = mandItem.prijs

}










