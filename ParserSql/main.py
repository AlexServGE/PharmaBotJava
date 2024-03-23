from ControllerDailyProcurements import ControllerDailyProcurements
from Input_configs.Pharma_categories import pharma_categories_percent_format_dict
from Input_configs.Federal_regions import federal_regions_dict
import datetime

start_time = datetime.datetime.now()
print(f'Начало сбора - {start_time}')

for pharma_category_title, pharma_list_percent_format in pharma_categories_percent_format_dict.items():
    controller_daily_procurements = ControllerDailyProcurements(pharma_list_percent_format,
                                                                pharma_category_title,
                                                                federal_regions_dict)

finish_time = datetime.datetime.now()
print(f'Окончание сбора - {finish_time}. Длительность - {finish_time - start_time}')
