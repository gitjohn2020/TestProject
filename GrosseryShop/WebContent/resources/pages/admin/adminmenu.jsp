<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div data-scroll-to-active="true" class="main-menu menu-fixed menu-dark menu-accordion menu-shadow">
      <!-- main menu header-->
      <div class="main-menu-header">
        <input type="text" placeholder="Search" class="menu-search form-control round"/>
      </div>
      <!-- / main menu header-->
      <!-- main menu content-->
      <div class="main-menu-content">
        <ul id="main-menu-navigation" data-menu="menu-navigation" class="navigation navigation-main">
          <li class=" nav-item"><a href="#" ng-click="doAction('uom')"><i class="icon-mail6"></i><span data-i18n="nav.form_layouts.form_layout_basic" class="menu-title"> UOM Master</span></a>
          </li>
          <li class="active"><a href="#" ng-click="doAction('itype')"><i class="icon-calendar5"></i><span data-i18n="nav.bootstrap_tables.table_basic" class="menu-title"> Item Type Master</span></a>
          </li>
          <li class="active"><a href="#" ng-click="doAction('item')"><i class="icon-calendar5"></i><span data-i18n="nav.bootstrap_tables.table_basic" class="menu-title"> Item Master</span></a>
          </li>
          
          </ul>
      </div>
      </div>
      