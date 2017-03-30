package com.gateside.autotesting.Gat.uia.webautomation.webcontrols;


import org.openqa.selenium.support.ui.Select;

import com.gateside.autotesting.Gat.uia.webautomation.webcontrols.WebUIControll;
import com.gateside.autotesting.Gat.dataobject.ActionMethod;
import com.gateside.autotesting.Gat.dataobject.CheckPointMethod;
import com.gateside.autotesting.Gat.dataobject.uielement.UIElement;
import com.gateside.autotesting.Gat.uia.webautomation.WebBrowser;

public class WebUIDropDownList extends WebUIControll
{


  private Select dropDownList = null;
  
  public WebUIDropDownList(WebBrowser webBrowser, UIElement elementNode)
  {
      super(webBrowser, elementNode);
      dropDownList = new Select(getUIElement(elementNode));
  }
  
  /// <summary>
  /// Select item by text
  /// </summary>
  /// <param name="item"></param>
 
  @ActionMethod
  public void select(String item)
  {
      dropDownList.selectByVisibleText(item);
  }

  /// <summary>
  /// Slect item by index
  /// </summary>
  /// <param name="index"></param>
  @ActionMethod
  public void selectByIndex(String index)
  {
      dropDownList.selectByIndex(Integer.valueOf(index));
  }

  /// <summary>
  /// De select all item
  /// </summary>
  @ActionMethod
  public void deSelectAll()
  {
      dropDownList.deselectAll();
  }

  /// <summary>
  /// Select multi item
  /// </summary>
  /// <param name="itemList">a item string array</param>
  @ActionMethod
  public void multiSelect(String[] itemList)
  {
      for(String  item : itemList)
      {
          dropDownList.selectByVisibleText(item);
      }
  }

  /// <summary>
  /// Get item count
  /// </summary>
  /// <returns></returns>
  @ActionMethod
  public String getItemLength() { return String.valueOf(dropDownList.getOptions().size()); }

  /// <summary>
  /// Get select value
  /// </summary>
  /// <returns></returns>
  @ActionMethod
  public String getSelectedValue() { return dropDownList.getFirstSelectedOption().getText(); }

  /// <summary>
  /// Check default value
  /// </summary>
  /// <param name="item"></param>
  /// <returns></returns>

  @CheckPointMethod
  public Boolean checkDefaultValue(String item)
  {
      return dropDownList.getFirstSelectedOption().getText().equals(item);
  }
 

  /// <summary>
  /// Check control is multi select
  /// </summary>
  /// <returns></returns>
  @CheckPointMethod
  public Boolean isMultiSelect()
  {
      return dropDownList.isMultiple();
  }
}
