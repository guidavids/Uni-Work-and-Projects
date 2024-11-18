using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class playerHealth : MonoBehaviour
{
    public float playerMaxHealth; // Maximum health the player can have
    public GameObject damageFX; // The particles that will pop when the player is damaged
    float playerCurrentHealth; // Player health at any given time
    Animator pcAnim; // Calling playerController script
    public AudioClip[] playerTakeHitSounds; // Player noise when hit by an enemy

    playerController pcContoller;

    public AudioClip playerDeathSound;
    AudioSource playerAS;

    // Variables to interact with HUD
    public Slider healthSlider;

    // Setting variable to store scene name
    string sceneName;

    // Start is called before the first frame update
    void Start()
    {
        // Initializing player health
        playerCurrentHealth = playerMaxHealth;
        pcContoller = GetComponent<playerController>();
        pcAnim = GetComponent<Animator>();

        // Initializing HUD
        healthSlider.maxValue = playerMaxHealth;
        healthSlider.value = playerMaxHealth;

        // Initializing Audio Source
        playerAS = GetComponent<AudioSource>();

        // Initializing Scene Management
        Scene currentScene = SceneManager.GetActiveScene();
        sceneName = currentScene.name;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void playerTakeDamage(float damage)
    {
        if(damage <= 0)
        {
            return;
        }

        playerCurrentHealth = playerCurrentHealth - damage; // Update the current player to correct value after taking damage

        // Choosing a random sound when player is hit
        int randomIndex = Random.Range(0, playerTakeHitSounds.Length); // Create a random index between 0 and the length of the array
        AudioClip randomTakeHitSound = playerTakeHitSounds[randomIndex]; // Set the random number to play a random sound in the array

        playerAS.clip = randomTakeHitSound; // Set the audio clip to the variable
        playerAS.Play(); // Play the audio whenever this method runs
        
        if (pcAnim.GetBool("isGrounded")) // Getting the boolean parameter in the Animator to see if player is on the ground
        {
            pcAnim.SetTrigger("playerTakeHit"); // Play the playerTakeHit animation via a trigger parameter
        }
        healthSlider.value = playerCurrentHealth; // Set the slider to current player health when player takes damage

        if(playerCurrentHealth <= 0)
        {
            //pcAnim.SetTrigger("playerDeath");
            AudioSource.PlayClipAtPoint(playerDeathSound, transform.position);
            gameObject.tag = "Untagged";
            pcKill();
        }
    }

    public void addHealth(float healthAmount)
    {
        playerCurrentHealth = playerCurrentHealth + healthAmount;
        if(playerCurrentHealth > playerMaxHealth)
        {
            playerCurrentHealth = playerMaxHealth;
        }
        healthSlider.value = playerCurrentHealth;
    }

    public void pcKill()
    {
        Instantiate(damageFX, transform.position, transform.rotation);
        Destroy(gameObject);

        if(sceneName == "Level 1")
        {
            SceneManager.LoadScene("Game Over Scene");
        }
        else if(sceneName == "Level 2")
        {
            SceneManager.LoadScene("Game Over Scene 1");
        }
        else if(sceneName == "Level 3")
        {
            SceneManager.LoadScene("Game Over Scene 2");
        }
    }
}
