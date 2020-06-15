import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css']
})
export class TableListComponent implements OnInit {

  dataList: any[];
  dataKeys: string[];

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.dataList = this.activatedRoute.snapshot.data.list;
    this.dataKeys = this.activatedRoute.snapshot.data.keys;

    console.log(this.dataList);
    console.log(this.dataKeys);
  }

}
