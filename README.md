## CleanSoup
###
### Dimensions

Modify *receiver.dimensions.DefaultDimensionsReceiver.getDimensions* method return to change the
panel dimensions. You can also create a new DimensionsReceiver class that implements **IDimensionsReceiver**
interface and set it in the dependency container.

### Words and blanks

Modify *receiver.word.DefaultWordReceiver.getWords* method return to change the 
words that will appear in the panel and *receiver.word.DefaultWordReceiver.getRandomCharacter* 
to define a new character to fill the blanks. You can also create a new WordReceiver 
class that implements **IWordsReceiver** interface and set it in the dependency container.