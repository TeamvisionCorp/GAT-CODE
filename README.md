#GAT_Maven_Public
GAT是一个数据驱动，代码，用例，数据相互分离的接口以及web ui自动化框架。

GATCore:最新版本：2.0.6
gatesideLib最新版本：2.0.5
Gattme最新版本：2.0.9


  GATCore 2.0.6  新功能

    1  修复属性值不能包含/的bug。 /做为xml的特定符号，不能包含在属性值中。请使用_来代替属性值中的/

  GATCore 2.0.5  新功能

    1  修复不能加载自定义UIControll的bug
    
  GATCore 2.0.4 新功能
    
    1  支持在DataFiles/xmls 文件夹中通过新建子文件夹方式管理TestCase文件，以及Parameters文件
    
    2  在TestCase.xml文件中，新增测试模块概念。即StepModule。测试模块形式上和TestCase一致，但不会生成新的用例。测试模块是作为公共模块被其他用例调用的对象。
    如下例子所示。
    
    //StepModule="true" 代表这是一个Module而不是一个用例
    <TestCase ID="Test00" Name="login" StepModule = "true">
 		<Step StepName="getDeviceIDLink" StepGroup="DeviceIDSteps"  StepAssembly="com.wanmei.mobile.iat.passport.steps." StepParameterID="test01UpdateInfoPost"/>
    	<Step StepName="regLoginLink" StepGroup="RegLoginSteps" StepAssembly="com.wanmei.mobile.iat.passport.steps."  StepParameterID="test01UpdateInfoPost"/>
    	<Step StepName="login" StepGroup="LoginSteps" StepParameterID="test01UpdateInfoPost"/>
    </TestCase>  
    
    //在下面的用里中可以调用，上面的模块。
    <TestCase ID="Test01" Name="test01FavouritePost">
        <Step StepName="login" StepModuleID="Test00" ModuleStepParameters="test01FavouritePost"  StepModule ="true"/>
    	<Step StepName="favourite" StepGroup="FavouriteSteps" StepParameterID="test01FavouritePost"/>
    </TestCase>