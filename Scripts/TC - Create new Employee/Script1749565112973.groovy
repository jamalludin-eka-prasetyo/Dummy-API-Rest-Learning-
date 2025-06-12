import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

def createNewEmployee = WS.sendRequest(findTestObject('Object Repository/POST - Create New Employee', [('name') : 'Malls', ('salary') : '2000000', ('age') : '29']))

def slurper = new groovy.json.JsonSlurper()
def responseCreate = slurper.parseText(createNewEmployee.getResponseText())
def statusResponse = responseCreate.status
def messageResponse = responseCreate.message
def nameResponse = responseCreate.data.name
def sallaryResponse = responseCreate.data.salary
def ageResponse = responseCreate.data.age
int idResponse = responseCreate.data.id as Integer

if (statusResponse == 'success') {
	if (createNewEmployee.getStatusCode() == 200) {
		KeywordUtil.logInfo('Message = ' + messageResponse)
		KeywordUtil.logInfo('Name = ' + nameResponse)
		KeywordUtil.logInfo('Sallary = ' + sallaryResponse)
		KeywordUtil.logInfo('Age = ' + ageResponse)
		KeywordUtil.logInfo('ID = ' + idResponse)
		GlobalVariable.id = idResponse
	}
} else {
    KeywordUtil.markFailed('Gagal Create a New Employee')
}

