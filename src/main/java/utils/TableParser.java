package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Evgen on 19.04.2017.
 */
public class TableParser {

    private WebElement table;
    private List<String> titleList;


    public TableParser(WebElement table) {
        this.table = table;
        getTitleFromTable();
    }


    private void getTitleFromTable() {
        WebElement thead = table.findElement(By.cssSelector("thead"));
        List<WebElement> elementsList = thead.findElements(By.cssSelector("th"));
        titleList = new LinkedList<>();
        elementsList.forEach(title -> titleList.add(title.getText().toLowerCase()));
    }

    private List<WebElement> getCellsFromTableRow(int rowNumber) {
        WebElement tbody = table.findElement(By.cssSelector("tbody"));
        List<WebElement> cellsFromRow = tbody.findElements(By.cssSelector("tr:nth-child(" + rowNumber + ") td"));
        return cellsFromRow;
    }

    private WebElement getRowFromTable(int rowNumber) {
        return table.findElement(By.cssSelector("tbody tr:nth-child(" + rowNumber + ")"));
    }

    private List<String> getDataFromTableRow(int rowNumber) {
        List<WebElement> cells = getCellsFromTableRow(rowNumber);
        List<String> result = new LinkedList<>();
        cells.forEach(text -> result.add(text.getText()));
        return result;
    }

    public String getFieldFromTableRow(int rowNumber, String fieldName) {
        List<String> allInfo = getDataFromTableRow(rowNumber);
        int indexOfField = titleList.indexOf(fieldName);
        return allInfo.get(indexOfField);
    }

    public String getFieldFromFirstTableRow(String fieldName) {
        return getFieldFromTableRow(1,fieldName);
    }
    public String getEmailFromTableRow(int rowNumber) {
        return getFieldFromTableRow(rowNumber, "@email");
    }
    public String getEmailFromFirstTableRow(){
        return getFieldFromTableRow(1, "@email");
    }


    /**
     *
     * @param rowNumber
     * @param buttonName
     * @return
     */
    public WebElement getButtonFromTableRow(int rowNumber, String buttonName) {
        WebElement row = getRowFromTable(rowNumber);
        if (row.findElements(By.cssSelector("span[title=\"" + buttonName + "\"]")).size() > 0)
            return row.findElement(By.cssSelector("span[title=\"" + buttonName + "\"]"));
        if (row.findElements(By.cssSelector("button[title=\"" + buttonName + "\"]")).size() > 0)
            return row.findElement(By.cssSelector("button[title=\"" + buttonName + "\"]"));
        if (row.findElements(By.cssSelector("a[title=\"" + buttonName + "\"]")).size() > 0)
            return row.findElement(By.cssSelector("a[title=\"" + buttonName + "\"]"));
        return null;
    }

    public WebElement getButtonFromTableRowByLinkElement(int rowNumber) {
        List<WebElement> webElementsList = getCellsFromTableRow(rowNumber);
        WebElement link = null;
        for (WebElement cell : webElementsList) {
            if (cell.findElements(By.className("glyphicon glyphicon-pencil")).size() > 0)
                link = cell.findElement(By.className("glyphicon glyphicon-pencil"));
        }
        return link;
    }

    public WebElement getButtonFromTableRowByButtonTitle(int rowNumber) {
        List<WebElement> webElementList = getCellsFromTableRow(rowNumber);
        WebElement button = null;
        for (WebElement cell : webElementList) {
            if (cell.findElements(By.className("glyphicon glyphicon-remove")).size() > 0)
                button = cell.findElement(By.className("glyphicon glyphicon-remove"));
        }
        return button;
    }


}
