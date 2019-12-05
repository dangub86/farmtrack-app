export const closeMixin = {
    methods: {
        close(id) {
          document.getElementById(id).style.display = "none";
        },
        closeAll() {
          for (
            var i = 0;
            i < document.getElementsByClassName("modal").length;
            i++
          ) {
            document.getElementsByClassName("modal")[i].style.display = "none";
          }
        },
        responseAfterSubmit() {
          console.log("mixin starts...");
          if (this.success) {
            console.log("mixin - is success");
            setTimeout(() => {
              console.log("mixin - closing all...");
              this.closeAll();
              console.log("mixin - closed all...");
              this.success = null;
              this.error = null;
              this.response = null;
            }, 3000);
          } else if (this.error) {
            this.message = "Something goes wrong... try again";
          }
        }
    }
}