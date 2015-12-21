package com.faidon.job.hwbs.un.client;

import com.faidon.job.hwbs.un.shared.NumberQty;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UniqueNumbers implements EntryPoint {
    public void onModuleLoad() {
        final Button genButton = new Button("Generate");
        genButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //"Generate"
                //"Stop"
                //"Regenerate"
            }
        });

        final Label progressLabel = new Label("Progress: 533.450");
        RootPanel.get("progress").add(progressLabel);

        List<NumberQty> data = new ArrayList<>();
        data.add(new NumberQty(1, "00104", 1));
        data.add(new NumberQty(2, "17984", 7));
        data.add(new NumberQty(3, "47654", 2));
        data.add(new NumberQty(4, "00436", 3));
        data.add(new NumberQty(5, "20320", 9));

        final CellTable<NumberQty> numbersTable = new CellTable<NumberQty>(new ProvidesKey<NumberQty>() {
            @Override
            public Object getKey(NumberQty item) {
                return item.id;
            }
        });
        numbersTable.setAutoHeaderRefreshDisabled(true);
        numbersTable.setAutoFooterRefreshDisabled(true);
        numbersTable.setWidth("100%");
        //numbersTable.setHeight("100%");
        numbersTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);

        ListDataProvider<NumberQty> dataProvider = new ListDataProvider<>(data);
        dataProvider.addDataDisplay(numbersTable);

        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        SimplePager pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(numbersTable);

        TextColumn<NumberQty> numberColumn =
                new TextColumn<NumberQty>() {
                    @Override
                    public String getValue(NumberQty object) {
                        return object.number;
                    }
                };
        numbersTable.addColumn(numberColumn, "First Name");
        numbersTable.setColumnWidth(numberColumn, 100, Style.Unit.PX);

        TextColumn<NumberQty> countColumn =
                new TextColumn<NumberQty>() {
                    @Override
                    public String getValue(NumberQty object) {
                        return Integer.toString(object.qty);
                    }
                };
        numbersTable.addColumn(countColumn, "Quantity");
        numbersTable.setColumnWidth(countColumn, 40, Style.Unit.PX);

        ColumnSortEvent.ListHandler<NumberQty> sortHandler = new ColumnSortEvent.ListHandler<>(dataProvider.getList());
        numberColumn.setSortable(true);
        sortHandler.setComparator(numberColumn, new Comparator<NumberQty>() {
            @Override
            public int compare(NumberQty o1, NumberQty o2) {
                return o1.number.compareTo(o2.number);
            }
        });
        countColumn.setSortable(true);
        sortHandler.setComparator(countColumn, new Comparator<NumberQty>() {
            @Override
            public int compare(NumberQty o1, NumberQty o2) {
                return Integer.compare(o1.qty, o2.qty);
            }
        });
        numbersTable.addColumnSortHandler(sortHandler);

        dataProvider.refresh();

        RootPanel.get("generate").add(genButton);
        RootPanel.get("uniqueNumbers").add(numbersTable);
        RootPanel.get("pager").add(pager);
    }
}
