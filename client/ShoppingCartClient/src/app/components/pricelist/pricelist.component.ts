import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgbModal,NgbModalOptions,ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { AppSettings } from 'src/app/app.settings';

/**
 * This component is for controlling the price list and price calculation.
 * 
 */
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
  displayPrice = {};
  quantity : number;
  errorVisibility : string = "invisible";
  successVisibility : string = "invisible";
  errorText : string = "";

  // Load the price list for all items when component loads.
  ngOnInit(): void {
    this.getPriceList();
  }

  // Returns the list of prices for all items.
  getPriceList(){
    this.http.get(AppSettings.API_ENDPOINT + "/pricelist?qty=50")
    .subscribe((data) => this.prices = data);
  }

  // Returns all items. 
  getItems(){
    this.http.get(AppSettings.API_ENDPOINT)
    .subscribe(data => this.items = data)
  }

  // Returns the price for a specific item and quanity for a given measurement. 
  calculatePrice(){
    if(this.selectedItem == ""){
      this.errorText = "Please select an item."
      this.errorVisibility = "visible";
      this.successVisibility = "invisible";
    }else if (this.measurement == ""){
      this.errorText = "Please select a measurement."
      this.errorVisibility = "visible";
      this.successVisibility = "invisible";
    }else if(this.quantity == null || this.quantity <=0){
      this.errorText = "Selected quantity should be more than 0."
      this.errorVisibility = "visible";
      this.successVisibility = "invisible";
    }else{
      this.http.get(AppSettings.API_ENDPOINT + "/" + this.selectedItem + "/price?qty=" + this.quantity + "&type=" + this.measurement)
      .subscribe(data => this.displayPrice = data , err=>console.log("error"));
      this.successVisibility = "visible";
      this.errorVisibility = "invisible";
    }
  }

  // Opens the modal view of price calculator.
  open(content) {
    this.resetModal();
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

  resetModal(){
    this.selectedItem = "";
    this.errorText = "";
    this.errorVisibility = "invisible";
    this.successVisibility = "invisible";
    this.selectedItem = "";
    this.measurement = "";
    this.quantity = null;
  }
}
