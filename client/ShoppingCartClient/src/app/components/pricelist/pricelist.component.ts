import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgbModal,NgbModalOptions,ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { AppSettings } from 'src/app/app.settings';

@Component({
  selector: 'app-pricelist',
  templateUrl: './pricelist.component.html',
  styleUrls: ['./pricelist.component.css']
})
export class PricelistComponent implements OnInit {

  constructor(private http:HttpClient,private modalService: NgbModal) { }
  prices;
  items;
  selectedItem : string = "";
  measurement : string = "";
  searchInput : string;
  modalOptions : NgbModalOptions;
  closeResult : string;
  displayPrice;
  quantity : number;
  errorVisibility : string = "invisible";
  errorText : string = "";


  ngOnInit(): void {
    this.getPriceList();
  }


  getPriceList(){
    this.http.get(AppSettings.API_ENDPOINT + "/pricelist?qty=50")
    .subscribe((data) => this.prices = data);
  }


  getItems(){
    this.http.get(AppSettings.API_ENDPOINT)
    .subscribe(data => this.items = data)
  }

  calculatePrice(){

    if(this.selectedItem == ""){
      this.errorText = "Please select an item."
      this.errorVisibility = "visible";
    }else if (this.measurement == ""){
      this.errorText = "Please select a measurement."
      this.errorVisibility = "visible";
    }else if(this.quantity == null || this.quantity <=0){
      this.errorText = "Selected quantity should be more than 0."
      this.errorVisibility = "visible";
    }else{
      this.http.get(AppSettings.API_ENDPOINT + "/" + this.selectedItem + "/price?qty=" + this.quantity + "&type=" + this.measurement)
      .subscribe(data => console.log(data));
    }


  }

  open(content) {
    this.getItems();
    this.modalService.open(content, this.modalOptions).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

}
